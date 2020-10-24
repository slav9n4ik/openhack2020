#!/usr/bin/python

import sys
from random import seed
from random import randint

#seed(1)
print('Argument List:', str(sys.argv))
print('ID:', randint(0, 999999), ", Value: ", randint(0, 999999))