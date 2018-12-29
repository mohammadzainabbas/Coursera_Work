import time, numpy as np

a = np.random.rand(1000000)
b = np.random.rand(1000000)
t1 = time.time()
c = np.dot(a,b)
t2 = time.time()
vectorized_time = t2 - t1
print("Result " + str(c))
print("Vectorized Version " + str(1000*(t2-t1)) + " ms")

c = 0
t1 = time.time()
for i in range(len(a)):
    c += a[i] * b[i]
t2 = time.time()
loop_time = t2 - t1
print("Result " + str(c))
print("For loop Version " + str(1000*(t2-t1)) + " ms")
print("Vectorized version is " + str((loop_time/vectorized_time)) + " times faster than loop version")