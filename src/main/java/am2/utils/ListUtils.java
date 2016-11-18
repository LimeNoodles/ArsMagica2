package am2.utils;

import java.util.ArrayList;
import java.util.List;

import com.google.common.collect.Lists;

public class ListUtils {
	
	@SafeVarargs
	public static <E> List<List<E>> getAllCombinationsOf(int elements, E... items) {
		List<List<E>> list = new ArrayList<>();
		if (elements == 0){
			list.add(new ArrayList<>());
			return list;
		} else if (elements > items.length)
			return list;
		List<E> groupWithoutX = Lists.newArrayList(items);
		E e = groupWithoutX.remove(groupWithoutX.size() - 1);
		
		List<List<E>> combosWithoutX = getAllCombinationsOf(elements, groupWithoutX);
		List<List<E>> combosWithX = getAllCombinationsOf(elements - 1, groupWithoutX);
		for (List<E> combo : combosWithX) {
	        combo.add(e);
	    }
	    list.addAll(combosWithoutX);
	    list.addAll(combosWithX);
		return list;
	}
	
	private static <E> List<List<E>> getAllCombinationsOf(int elements, List<E> items) {
		List<List<E>> list = new ArrayList<>();
		if (elements == 0){
			list.add(new ArrayList<>());
			return list;
		} else if (elements > items.size())
			return list;
		List<E> groupWithoutX = Lists.newArrayList(items);
		E e = groupWithoutX.remove(groupWithoutX.size() - 1);
		
		List<List<E>> combosWithoutX = getAllCombinationsOf(elements, groupWithoutX);
		List<List<E>> combosWithX = getAllCombinationsOf(elements - 1, groupWithoutX);
		for (List<E> combo : combosWithX) {
	        combo.add(e);
	    }
	    list.addAll(combosWithoutX);
	    list.addAll(combosWithX);
		return list;
	}
	
	public static int c (int n, int k) {
		return factorial(n) / (factorial(k) * factorial(n - k));
	}
	
	public static int factorial(int x) {
		int i = 1;
		for (int j = 1; j <= x; j++) {
			i*=j;
		}
		return i;
	}
}
