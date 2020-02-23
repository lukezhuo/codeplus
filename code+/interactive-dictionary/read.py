with open("vegetables.txt", "w") as myfile:
    myfile.write("Tomato\nCucumber\nOnion")
    myfile.write("\nGarlic")
with open("vegetables.txt", "a") as myfile:
    content = myfile.write("\nOkra")

print(content)
