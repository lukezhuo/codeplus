import os #standard
import time #builtin
import pandas #third-party

while True: 
    if os.path.exists("files/temps.txt"):
        data = pandas.read_csv("files/temps.txt")
        print(data.mean()["st1"])
    else:
        print("file does not exist")
    time.sleep(10)