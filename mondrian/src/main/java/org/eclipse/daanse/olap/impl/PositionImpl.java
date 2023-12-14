package org.eclipse.daanse.olap.impl;

import org.eclipse.daanse.olap.api.element.Member;
import org.eclipse.daanse.olap.api.result.Position;

import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

public class PositionImpl implements Position {

    public PositionImpl(Object tupleList, int index) {
    }

    @Override
    public List<Member> getMembers() {
        return null;
    }

    @Override
    public int size() {
        return 0;
    }

    @Override
    public boolean isEmpty() {
        return false;
    }

    @Override
    public boolean contains(Object o) {
        return false;
    }

    @Override
    public Iterator<Member> iterator() {
        return null;
    }

    @Override
    public Object[] toArray() {
        return new Object[0];
    }

    @Override
    public <T> T[] toArray(T[] ts) {
        return null;
    }

    @Override
    public boolean add(Member member) {
        return false;
    }

    @Override
    public boolean remove(Object o) {
        return false;
    }

    @Override
    public boolean containsAll(Collection<?> collection) {
        return false;
    }

    @Override
    public boolean addAll(Collection<? extends Member> collection) {
        return false;
    }

    @Override
    public boolean addAll(int i, Collection<? extends Member> collection) {
        return false;
    }

    @Override
    public boolean removeAll(Collection<?> collection) {
        return false;
    }

    @Override
    public boolean retainAll(Collection<?> collection) {
        return false;
    }

    @Override
    public void clear() {

    }

    @Override
    public Member get(int i) {
        return null;
    }

    @Override
    public Member set(int i, Member member) {
        return null;
    }

    @Override
    public void add(int i, Member member) {

    }

    @Override
    public Member remove(int i) {
        return null;
    }

    @Override
    public int indexOf(Object o) {
        return 0;
    }

    @Override
    public int lastIndexOf(Object o) {
        return 0;
    }

    @Override
    public ListIterator<Member> listIterator() {
        return null;
    }

    @Override
    public ListIterator<Member> listIterator(int i) {
        return null;
    }

    @Override
    public List<Member> subList(int i, int i1) {
        return null;
    }
}
