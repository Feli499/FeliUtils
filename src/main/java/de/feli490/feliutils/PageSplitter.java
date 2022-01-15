package de.feli490.feliutils;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class PageSplitter<T> {

    private final List<T> list;

    private final int pageEntries;

    public PageSplitter(Collection<T> list, int pageEntries) {
        this.list = new ArrayList<>(list);
        this.pageEntries = pageEntries;
    }

    public void sort(Comparator<T> comparator) {
        list.sort(comparator);
    }

    public boolean isValidPage(int page) {

        if (page < 0) {
            return false;
        }
        return page <= getMaxPage() - 1;
    }

    public Map<Integer, List<T>> getPages() {

        Map<Integer, List<T>> pages = new HashMap<>();

        int maxPage = getMaxPage();
        for (int page = 0; page < maxPage; page++) {
            List<T> pageList = new ArrayList<>();
            for (int i = 0; i < pageEntries && (page) * pageEntries + i < list.size(); i++) {
                pageList.add(list.get((page) * pageEntries + i));
            }

            pages.put(page, pageList);
        }

        return pages;
    }

    public int getMaxPage() {

        return (list.size() - 1) / pageEntries + 1;
    }

    public List<T> getPageContent(int page) {
        return getPages().get(page);
    }

}
