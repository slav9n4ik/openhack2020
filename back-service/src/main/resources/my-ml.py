#!/usr/bin/python

import sys
from random import seed
from random import randint

#seed(1)
value = randint(0, 99999)
print('Argument List:', str(sys.argv))
print('Result: ', value)