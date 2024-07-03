package MUPRG1.Rekursion;

public class anwenden {

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

    static SinglyLinkedList remove(final SinglyLinkedList list, final int value) {
        if (list.root() == null) {
            return list;
        }
        if (list.root().value() == value) {
            return new SinglyLinkedList(list.root().next());
        }
        return new SinglyLinkedList(remove(list.root(), value));
    }

    static ListNode remove(ListNode current, final int value) {
        if (current.next() == null) {
            return current;
        }
        if (current.next().value() == value) {
            return new ListNode(current.value(), current.next().next());
        }
        return new ListNode(current.value(), remove(current.next(), value));
    }

}
