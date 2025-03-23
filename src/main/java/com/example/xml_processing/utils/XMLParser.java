package com.example.xml_processing.utils;

public interface XMLParser {
    <E> E importFile(Class<E> clazz, String path);
    <E> void exportFile(E e, String path);
}
