import torch
import numpy as np

a = np.array([2,3,4])

x = np.array([[1], [2], [3]])
y = np.array([4, 5, 6])
b = np.broadcast(x, y)


y = torch.rand(4, 4)
print(y)
z = torch.rand(4, 4)
print(z)
x = torch.rand(4, 1)
mask = x.ge(0.5)
print(mask)
y.masked_scatter_(mask, z)
print(y)


# from .hero import Hero
# h = Hero()

# import hero
# h = hero.Hero()

# herofolder.__init__什么都没实现，这时采用的方�?1
# import herofolder.hero
# h = herofolder.hero.Hero()
# print(h.name)
# 采用的方�?2
# from herofolder.hero import *
# h = Hero()

# from herofolder import hero
# h = hero.Hero()
# import herofolder
# h = herofolder.hero.Hero()

# x = 1
# y = 2
# print(x, y)
# b = [1, 2, 3, 4, 5]
# print(["a " for i in range(5)])
# print('...'.join("{:d}".format(b[i]) for i in range(5)))