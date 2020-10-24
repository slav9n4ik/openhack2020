#!/usr/bin/python

import sys
from random import seed
from random import randint
from time import sleep

#seed(1)
print('Argument List:', str(sys.argv))
sleep(15)
print('ID:', randint(0, 999999), ", Value: ", randint(0, 999999))