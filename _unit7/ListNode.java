// ListNode.java (used by LinkedListDemo.java)
/** Unit 7
  * @author: Henry Leitner
  * @version: Last modified on April 7, 2020
  */

public class ListNode
{
    private String data;
    private ListNode link;

    public ListNode()
    {
        link = null;
        data = null;
    }
    

    public ListNode (String newData, ListNode linkValue)
    {
        data = newData;
        link = linkValue;
    }
    

    public void setData (String newData)
    {
        data = newData;
    }
    

    public String getData ()
    {
        return data;
    }


    public void setLink (ListNode newLink)
    {
        link = newLink;
    }

    public ListNode getLink()
    {
        return link;
    }
}
