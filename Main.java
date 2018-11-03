class Main
{
    public static void main (String[] args) throws java.lang.Exception
    {
        // create BTree of order 3
        BTree tree = new BTree(3);

        tree.Insert(5);
        tree.Insert(6);
        tree.Insert(7);
        tree.Insert(8);
        tree.Insert(9);
        tree.Insert(10);
        tree.Remove(7);
        tree.Remove(8);
        tree.Show();
    }
}
