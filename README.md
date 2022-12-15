# Puddle
A super simple language.

# Getting started
Just run the `run.sh` file. The source file is currently specified in the `Puddle.java` file.

# Syntax
Numbers: 42, 1.2, ...

Booleans: true, false

Unary: ! for booleans, - for numbers

Binary: +, -, *, /, %, \<=, >=, ==, !=

Functions:
```
fn test(a b)
  ret a + b
```

If:
```
if 10 == 20
  ...
else
  ...
```

For:
```
for x in \[1, 2, 3]
  ...
```

Map: `{10: true, 20: false}`

List: `[1, 2, 3]`
Tuple: `(1, 2, 3)`
