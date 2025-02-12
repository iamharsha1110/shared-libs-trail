#!/bin/bash
sudo apt update
sudo apt-get install -y stress unzip
sudo curl "https://awscli.amazonaws.com/awscli-exe-linux-x86_64.zip" -o "awscliv2.zip"
sudo unzip -o awscliv2.zip  # Adding -o to overwrite if the file already exists
sudo ./aws/install
aws --version
sudo wget https://s3.amazonaws.com/amazoncloudwatch-agent/ubuntu/amd64/latest/amazon-cloudwatch-agent.deb
sudo dpkg -i amazon-cloudwatch-agent.deb
sudo systemctl start amazon-cloudwatch-agent
sudo systemctl enable amazon-cloudwatch-agent

cat <<EOL | sudo tee /opt/aws/amazon-cloudwatch-agent/bin/config.json
{
    "metrics": {
        "namespace": "Final-Test", 
        "append_dimensions": {
            "InstanceId": "\${!instance_id}"
        },
        "metrics_collected": {
            "mem": {
                "measurement": [
                    "mem_used_percent",
                    "mem_available"
                ],
                "metrics_collection_interval": 60
            }
        }
    }
}
EOL

sudo /opt/aws/amazon-cloudwatch-agent/bin/amazon-cloudwatch-agent-ctl \
    -a fetch-config \
    -s \
    -c file:/opt/aws/amazon-cloudwatch-agent/bin/config.json

sudo systemctl restart amazon-cloudwatch-agent
