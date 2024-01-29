package com.edu.pb.domain.service;

import lombok.Getter;

import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import java.util.stream.IntStream;

@Getter
public class PhoneBookService {

    private final TableModel tableModel;

    public PhoneBookService() {
        // TODO read/write data from file
        final int columns = 5;
        final int rows = 30;
        tableModel = new DefaultTableModel(generateTestData(columns, rows), generateTestDataColumnNames(columns));
    }

    private Object[][] generateTestData(final int columns, final int rows) {
        final String[][] strings = new String[rows][];
        for (int rowInd = 0; rowInd < rows; rowInd++) {
            strings[rowInd] = new String[columns];
            for (int colInd = 0; colInd < columns; colInd++) {
                strings[rowInd][colInd] = String.format("Value[%s,%s]", rowInd, colInd);
            }
        }
        return strings;
    }

    private String[] generateTestDataColumnNames(final int columns) {
        return IntStream.rangeClosed(1, columns)
                .mapToObj(ind -> "Column " + ind)
                .toArray(String[]::new);
    }
}
