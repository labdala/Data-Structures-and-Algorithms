package assn04;


import java.util.*;

public class NonEmptyBST<T extends Comparable<T>> implements BST<T> {
	private T _element;
	private BST<T> _left;
	private BST<T> _right;

	public NonEmptyBST(T element) {

		_left = new EmptyBST<T>();
		_right = new EmptyBST<T>();
		_element = element;
	}

	// TODO: insert
	@Override
	public BST<T> insert(T element) {
		int elementInt = convertToInt(element);
		int _elementInt = convertToInt(_element);
//		System.out.println("-----------------");
//		System.out.println("HEAD_element="+_element+", _elementInt="+_elementInt);
//		System.out.println("want to introduce element="+element+", elementInt="+elementInt);
		BST<T> returningBST = new EmptyBST<T>();
		if(elementInt<_elementInt)
		{
			if(_left.isEmpty()) {
//				System.out.println("introduce in the left");
				_left = new NonEmptyBST<T>(element);
				return this;
			}
			else
			{
//				System.out.println("keep going");
				_left.insert(element);
			}
		}
		else if(elementInt>_elementInt)
		{
			if(_right.isEmpty()) {
//				System.out.println("introduce in the right");
				_right = new NonEmptyBST<T>(element);
				return this;
			}
			else
				_right.insert(element);
		}
//		else
//		{
//			throw new Exception("Not implemented");
//		}

		return this;
	}

	public int convertToInt(T element)
	{
		// convert to string
		String elementStr = String.valueOf(element);
		int[] value = new int[1];
		try {
			value[0] = Integer.parseInt(elementStr);
		} catch (NumberFormatException e) {
			// convert to array of char
			char[] ch = new char[elementStr.length()];
			for (int i=0; i<elementStr.length();i++)
				ch[i] = elementStr.charAt(i);

			//convert into int
			value[0] = 0;
			for (int i=0; i<elementStr.length();i++)
			{
				value[0] +=(int) ch[i];
			}
		}

		return value[0];
	}


	// TODO: remove
	@Override
	public BST<T> remove(T element) {
		if (this.getLeft().isEmpty() && this.getRight().isEmpty()) { // this does not seem to work
			return new EmptyBST<T>();
		}
		else if (this.getLeft().isEmpty()) {
			return _right;
		}
		else if (this.getRight().isEmpty()) {
			return _left;
		}
		else if (element.compareTo(_element)==0) {
			BST<T> min = findMinNode(_right);
			remove(min.getElement());
			_element = min.getElement();
		}
		else{
			if(element.compareTo(_element)>0)
				_right = _right.remove(element);
			else if(element.compareTo(_element)<0)
				_left = _left.remove(element);
		}
		return this;
	}

	public BST<T> searchElement(T element)  { // NOT WORKING per test in printBreadthFirstTraversal
												// it returns the global head of the tree
		BST<T> result = new EmptyBST<T>();
		if(element.compareTo(_element)<0)
			_left.searchElement(element);
		else if(element.compareTo(_element)>0)
			result = _right;
		else if(element==_element)
			return result;
		return result.searchElement(element);
	}
//
//	public void deleteGivenNode(BST<T> bst) { // need help
//		if(bst.getLeft().isEmpty() && bst.getRight().isEmpty())
//			bst = new EmptyBST<T>(); // does this look ok to delete a node?
//		else if(bst.getLeft().isEmpty())
//		{
//			_element = _right.getElement();
//			_right = new EmptyBST<T>();
//		}
//		else if(bst.getRight().isEmpty())
//		{
//			_element = _left.getElement();
//			_left = new EmptyBST<T>();
//		}
//		else
//		{
//			BST<T> sucessor = findMinNode(_right); // declaration is wrong here?
//			_element = sucessor.getElement();
//			deleteGivenNode(sucessor);
//		}
//	}

	public BST<T> findMinNode(BST<T> bst) { // ok!
		if(bst.getLeft().isEmpty())
			return bst;
		else
			return(findMinNode(bst.getLeft()));
	}



	// TODO: printPreOrderTraversal
	@Override
	public void printPreOrderTraversal() { // ok!
		System.out.print(_element.toString()+" ");
		if(!_left.isEmpty())
			_left.printPreOrderTraversal();
		if(!_right.isEmpty())
			_right.printPreOrderTraversal();
	}

	// TODO: printPostOrderTraversal
	@Override
	public void printPostOrderTraversal() { // need help
		if(!this.isEmpty())
		{
			if(!_left.isEmpty())
				_left.printPostOrderTraversal();
			if(!_right.isEmpty())
				_right.printPostOrderTraversal();

			System.out.print(_element.toString() + " ");
		}
	}

	// TODO: printBreadthFirstTraversal
//	@Override
//	public void printBreadthFirstTraversal() {
//		Queue<T> queue = new LinkedList<T>();
// 		BST<T> bst = this;
//		 int c=0;
//		while(!(queue.isEmpty()) || c==0)
//		{
//			queue.add(bst.getElement());
//			if (!bst.getLeft().isEmpty()) {
//				queue.add(_left.getElement());
//			}
//			if (!bst.getRight().isEmpty()) {
//				queue.add(_right.getElement());
//			}
//			T removed = queue.remove();
//			bst =  searchElement(queue.peek());
//			System.out.print(removed.toString() + " ");
//			c++;
//		}
////
//	}



	@Override
	public void printBreadthFirstTraversal() {
		Queue<BST<T>> queue = new LinkedList<>();
		BST<T> bst = this;
		queue.add(this);
		while(!(queue.isEmpty()) )
		{
			BST<T> removed = queue.remove();
			System.out.print(removed.getElement().toString() + " ");

			if (removed.getLeft().isEmpty()) {
				queue.add(removed.getLeft());
			}
			if (this.getRight().isEmpty()) {
				queue.add(removed.getRight());
			}
		}
	}

	// GetHeight of A Tree

	@Override
	public int getHeight() {
		   return Math.max(_left.getHeight(), _right.getHeight())+1;
	}


	@Override
	public BST<T> getLeft() {
		return _left;
	}

	@Override
	public BST<T> getRight() {
		return _right;
	}

	@Override
	public T getElement() {
		return _element;
	}

	@Override
	public boolean isEmpty() {
		return false;
	}

}


