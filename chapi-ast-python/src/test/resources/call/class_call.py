# noinspection PyInterpreter
class Employee:
    def __init__(self, name):
        self.name = name

    def displayEmployee(self):
        print "Name : ", self.name

emp = Employee("Zara")
emp.displayEmployee()
