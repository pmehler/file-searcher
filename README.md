# file-searcher
User can search through a Binary Search Tree filled with text from the given directory's subsequent children and return the location of the searched word.

The Word class has a constructor, getter/setter methods, and a compare to method for word objects. 
The main method calls the scanfiles() method which iterates through each file in the directory and fills the BST with all of the words and each word's arrayList with the proper file names.  
After the tree is filled, the user is prompted for one of three possibilities.  "s" searches through the tree and returns the ArrayList associated with each word object, the "a" does "s" but for all words, and "q" quits.

Flaws:
The input defaults to quit if s or a is not inputed. 
