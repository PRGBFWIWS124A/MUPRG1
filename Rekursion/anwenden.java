package MUPRG1.Rekursion;

public class anwenden{

    static SinglyLinkedList addFirst(final SinglyLinkedList list, final int value) {
        return new SinglyLinkedList(new ListNode(value, list.root()));
    }

    static SinglyLinkedList addLast(final SinglyLinkedList list, final int value) {
        return new SinglyLinkedList(addLast(list.root(), value));
    }

    static ListNode addLast(ListNode current, final int value) {
        if (current == null)
            return new ListNode(value, null);
        else
            return new ListNode(current.value(), addLast(current.next(), value));
    }

    static String toString(final SinglyLinkedList list) {
        return toString(list.root());
    }

    static String toString(final ListNode current) {
        if (current == null) {
            return "";
        } else {
            return "," + current.value() + toString(current.next());
        }
    }

    

}
