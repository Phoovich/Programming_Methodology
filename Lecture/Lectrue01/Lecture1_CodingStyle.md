# **Coding Style**

## **Outline**

- [**Format**](#format)
- [**Naming (Meaningful)**](#naming-meaningful)
- [**Conditional Statements**](#conditional-statements)
- [**Class**](#class)
- [**Method**](#method)
- [**Example**](#example)

---

## **Format**

- **using momospace font (Courier)**
- **Comment (beginnig of every file, unobvious step)**
- **A space before and after an opeator i.e. `x + y` rather than `x+y`**
- **Avoid long satement in one line**

---

## **Naming Meaningful**

- **Class name**: <u>**_singular noun_**</u> begins with <u>**_uppercase_**</u> letter.
- **Method name**: <u>**_Verb_**</u> begins with <u>**_lowercase_**</u> letter.
- **variable name**: <u>**_Noun_**</u> begins with <u>**_lowercase_**</u> letter.
  - For **boolean** variables, use *is*XXX or *has*XXX
- **constant**: <u>**Noun**</u> with all <u>**_uppercase_**</u> letter.
  - double PI, MAX, MAX_SPEED

---

## **Conditional**

- **if-elsel** can be used instead of series of if's?
- use `.equals()` to compare string/reference not ==

---

## **Class**

- use get/set for private fields
  - **Do not** use public field
- Preare constructor(s)
- Don't forget to write equals(), toString()

---

## Method

- small, It shouldn't be too long.
- one method one task
- Avoid duplicated code

---

## Coding

- avoid magic number, use constant instead

---

## Example

### indent style

- in mac use cmd + shift + f
- in window use crtl + shift + f

---

### Field, class, and method declarations

```java
// Bad
final static private String value;

// Good
private final static String value;

```

---

### Include units in variable names

```java
long pollIntervalMs; // add Ms --> Millie second
int filleSizeGb; // add Gb --> Gifabyte
```

---

### Don't embed metadata in variable names

```java
// Bad.
Map<Interger, User> idToUserMap; // should: inToUser
String valueString; // should: value

```
