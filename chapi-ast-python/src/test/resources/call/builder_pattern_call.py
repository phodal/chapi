## https://stackoverflow.com/questions/17321167/calling-chains-methods-with-intermediate-results

class Person:
    def setName(self, name):
        self.name = name
        return self  ## this is what makes this work

    def setAge(self, age):
        self.age = age;
        return self;

    def setSSN(self, ssn):
        self.ssn = ssn
        return self


p = Person()
p.setName("Hunter").setAge(24).setSSN("111-22-3333")
