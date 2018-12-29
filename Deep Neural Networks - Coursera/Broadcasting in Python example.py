import numpy as np

A = np.array([[56, 0, 4.4, 68],
              [1.2, 104, 52, 8],
              [1.8, 135, 99, .9]])

calories = A.sum(axis=0)

percentage = 100*A/calories.reshape(1,4)

print (percentage)