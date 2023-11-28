// FindNodeThatPointsToLastNode.java

/**
 * FindNodeThatPointsToLastNode is a program class that produces linked lists
 * of various sizes and displays various details about the list, including
 * the 2nd-to-the-last node in each list. That's an interesting capability
 * because you need to identify that node when you delete the item at the tail
 * end of a linked list.
 *
 * @author  David Habermehl
 * @version Last modified 18_Feb_2020
 **/
class FindNodeThatPointsToLastNode {
    private static final int NUMBEROFLISTS = 4;

    public static void main( String[] args ) {
        for (int i=0; i<NUMBEROFLISTS; i++ ) {
            // Create a linked list with i QueueNodes.
            KnownGoodLinkedList list = new KnownGoodLinkedList( i );

            // Display
            System.out.printf( "List #%d: %s\n", i, list );
            System.out.printf( "head: %s\n", KnownGoodLinkedList.extractAddress( list.getHead() ) );
            KnownGoodLinkedList.QueueNode secondToLastNode = null;
            try {
                secondToLastNode = list.get2ndToLastNode();
            }
            catch( Exception e ) {
                System.out.printf( "Caught exception: %s\n", e );
            }
            System.out.printf( "2nd-to-last: %s\n\n", KnownGoodLinkedList.extractAddress( secondToLastNode ) );
        }
    }
}



/**
 * KnownGoodLinkedList is a template class that produces a linked list
 * that is guaranteed to be bug free.
 *
 * @author  David Habermehl
 * @version Last modified 18_Feb_2020
 **/
class KnownGoodLinkedList {

    /**
     * get2ndToLastNode locates the Queuenode that points to the QueueNode
     * at the tail end of the list.
     *
     * @return  For lists that have >=2 QueueNodes, returns the Queuenode
     *          that points to the QueueNode at the tail end of the list
     *          For lists that have <2 QueueNodes, returns null
     */
    public QueueNode get2ndToLastNode() {
        // Start with this.head.
        // Follow the links until you reach the end of the list.
        // Retain enough detail to:
        //     1. (for lists that have >=2 QueueNodes):
        //            Return the QueueNode that points to the QueueNode at the tail end of the list.
        //     2. (for lists that have <2 QueueNodes):
        //            Return null

        /////////////////////////////////////////////////
        // WE'LL IMPLEMENT THIS METHOD IN SECTION.     //
        // CAN YOU CREATE WORKING CODE BEFORE SECTION? //
        /////////////////////////////////////////////////

        return null;
    }



    /**
     * QueueNode is a template class For the nodes in the KnownGoodLinkedList
     *
     * @author  David Habermehl
     * @version Last modified 18_Feb_2020
     **/
    class QueueNode {
       private Object    item;
       private QueueNode link;
    }



    // Instance variables
    private QueueNode[] linkedListNodes;
    private QueueNode head;



    // Constructors
    public KnownGoodLinkedList() { this( 0 ); }
    public KnownGoodLinkedList( int listSize ) {
        System.out.printf( "Creating a list that contains %d nodes\n", listSize );
        if ( listSize < 0 ) { throw new IllegalArgumentException(); }
        else {
            this.linkedListNodes = new QueueNode[listSize];
            if ( listSize == 0 ) {
                this.head = null;
            }
            else {
                // There are >=1 nodes. Create first node and point this.head to it.
                this.linkedListNodes[0] = makeQueueNode( 0 );
                this.head = this.linkedListNodes[0];

                // If there are >=2 nodes, construct the additional nodes and
                // initialize links.
                for (int i=2; i<=listSize; i++) {
                    this.linkedListNodes[i-1] = makeQueueNode( i );
                    this.linkedListNodes[i-2].link = this.linkedListNodes[i-1];
                }
            }
        }
    }



    /**
     * extractAddress distills an address from an object address of the form classname@address
     *
     * @param   node    The object whose address we want.
     * @return          The address portion of  classname@address.
     */
    static String extractAddress( QueueNode node ) {
        // Since QueueNde does not have its own toString() method, it inherits Object's toString() method.
        // Therefore node.toString() looks like KnownGoodLinkedList$QueueNode@6b884d57
        // The regular expression "^.*@" matches everything up to and including the '@'
        // So the replaceFirst String instance method replaces everything up to and including
        // the '@' with the empty String.
        return node==null ? null : String.format( "%8s", node.toString().replaceFirst( "^.*@", "" ) );
    }



    /**
     * getHead() returns the list's head instance variable.
     *
     * @return   The list's head instance variable.
     */
    public QueueNode getHead() { return this.head; }



    /**
     * makeQueueNode constructs a new QueueNode and initializes its item
     * and link instance variables. This method exists so that I can avoid
     * adding initialization code to the QueueNode class. Instead, I can
     * use Dr. Leitner's QueueNode class with no modificatons.
     *
     * @param   item    We'll use this for the new QueueNode's item field.
     * @return          The new QueueNode.
     */
    private QueueNode makeQueueNode( int item ) {
        QueueNode queueNode = new QueueNode();
        queueNode.item = item;
        queueNode.link = null;
        return queueNode;
    }



    /**
     * toString() returns a String representation of the list.
     *
     * @return   A String representation of the list.
     */
    public String toString() {
        String result = "";
        QueueNode next = this.head;
        while( next != null ) {
            result += this.queueNodeToString( next );
            next = next.link;
        }
        return result;
    }



    /**
     * queueNodeToString() returns a String representation of a QueueNode.
     * This method exists so that I can avoid adding a toString() method to
     * the QueueNode class. Instead, I can use Dr. Leitner's QueueNode class
     * with no modificatons.
     *
     * @param   queueNode    The QueueNode whose String representation is desired.
     * @return  A String representation of queueNode.
     */
    static String queueNodeToString( QueueNode queueNode ) {
        String queueNodeAddress = extractAddress( queueNode ),
               queueNodeItem    = ""+queueNode.item,
               queueNodeLink    = extractAddress( queueNode.link );
        return queueNode==null ? "null" : String.format( "[%s:%s,%s]   ", queueNodeAddress, queueNodeItem, queueNodeLink );
    }
}
