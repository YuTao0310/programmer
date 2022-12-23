def merge(s1,s2):
    i = j = 0
    length = len(s1 + s2)
    s = []
    while i + j < length:
        if j==len(s2) or (i<len(s1) and s1[i]<s2[j]):
            s.append(s1[i])
            i += 1
        else:
            s.append(s2[j])
            j += 1
    return s

arrays = [[1, 2, 3], [2, 4], [3, 4, 6]]
start = 2
end = 5
result = set() #可以考虑用hashset
s = arrays[0]
for i in range(len(arrays) - 1):
    s1 = s 
    s2 = arrays[i + 1]
    s = merge(s1, s2) 

for element in s:
    if element >= start and element < end :
        result.add(element)
    if element > end:
        break
print(result)

