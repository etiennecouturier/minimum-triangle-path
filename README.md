### Scope

The program reads lines of numbers separated by space (a triangle of numbers) and produces the min triangle path.

### How to run

Check out the project from github.
Run MinTrianglePathEnhanced object and provide input on console.
To start the processing press Ctrl+D.
There are also tests that can be run.

### Implementation

Started the implementation with mutable data structures like Array
just to test the dynamic programming solution (MinTrianglePath).
It seems to work with the big dataset. But I suspect this is only because
in the function findPath we always take the first element with the specific input provided,
hence we are not traversing the linked list in O(n) time.

MinTrianglePathEnhanced is the final solution where I only use immutable data
structure (List). Even though it takes a few seconds, it seems to work with the
big dataset. With the prescribed 500 rows it runs instantaneously (on my machine).

### Optimization

To avoid premature optimization, loaded the whole data into memory. This does not
seem to be a problem at this stage. If bigger amounts of data are expected, it should be considered
to use lazy data structures like streams.

### Commit history

Unfortunately forgot to commit in the meantime. This is why you have two separate files
indicating progress.

### Test with file

Did not want to pollute the code.
Similar snippet can be pasted if testing with a file is needed:
```scala
  print(findSmallestSumPathLeafIndex(readFromFileToList("org/istvan/algo/be_data_small.txt")))

  private def readFromFileToList(fileName: String): List[List[Int]] =
    fromInputStream(MinTrianglePathEnhanced.getClass
      .getResourceAsStream(fileName))
      .getLines
      .toList
      .map(_.split(" ")
        .toList.map(_.toInt))
```
