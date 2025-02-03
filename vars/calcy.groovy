def add(x, y, z) {
    print "Sum of ${x}, ${y} & ${z} is ${x + y + z}"
}

def sub(a, b) {
    print "Subtraction of ${a} and ${b} is ${a - b}"
}

def mul(x, y, z) {
    print "Multiplication of ${x}, ${y} & ${z} is ${x * y * z}"
}

def div(a, b) {
    if (b == 0) {
        print "Division by zero is undefined"
    } else {
        print "Division of ${a} & ${b} is ${a / b}"
    }
}
