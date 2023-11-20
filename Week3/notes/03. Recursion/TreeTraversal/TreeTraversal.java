// Demonstrates recursive creation and traversal of trees.
class TreeTraversal {
    public static void main(String [] args) {

        System.out.println( "\n\n\nCREATE TREE\n" );
        Tree tree = new Tree();

        System.out.println( "\n\nTRAVERSE TREE (ROOT, LEFT SUBTREE, RIGHT SUBTREE)\n" );
        tree.traverseRootLeftRightRecursively();

        System.out.println("\n");
    }
}



// Implements a binary tree, which is a tree where nodes have 0 or 1 left subtrees and 0 or 1 right subtrees.
class Tree {

    // Instance variables
    private Tree   leftSubtree,
                   rightSubtree;
    private Object data;

    // 0-arg constructor
    public Tree() {
        this( "root", 0 );
    }

    //                                        Level  0    1    2    3    4    5
    final double [] probabilityOfCreatingSubtree = { 1.0, 0.8, 0.7, 0.4, 0.2, 0.0 };

    // 2-arg constructor
    public Tree( Object data, int level ) {

        this.data = data;
        this.leftSubtree = this.rightSubtree = null;

        System.out.printf( "%1d (%3.1f) %s%s\n", level, probabilityOfCreatingSubtree[level], indent( level ), data );

        if ( Math.random() < probabilityOfCreatingSubtree[level] ) {
            this.leftSubtree  = new Tree( (level==0 ? "" : data)+"L",  level+1 );
        }
        if ( Math.random() < probabilityOfCreatingSubtree[level] ) {
            this.rightSubtree = new Tree( (level==0 ? "" : data)+"R",  level+1 );
        }
    }



    // Recursively traverse the tree by first visiting the root, then the left subtree, then the right subtree.
    // Base case(s): subtrees are null
    // Recursive case(s): traverse non-null subtrees
    public void traverseRootLeftRightRecursively() {
        System.out.printf( "%s ", this.data );
        if ( this.leftSubtree  != null ) { this.leftSubtree.traverseRootLeftRightRecursively (); }
        if ( this.rightSubtree != null ) { this.rightSubtree.traverseRootLeftRightRecursively(); }
    }



    // Helper method to indent according to level
    static final String INDENT = "......";
    private static String indent( int level ) {
        String result = "";
        for( int i=0; i<level; i++ ) { result += INDENT; }
        return result;
    }
}

