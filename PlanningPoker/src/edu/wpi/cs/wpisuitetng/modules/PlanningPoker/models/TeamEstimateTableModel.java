/*******************************************************************************
 * Copyright (c) 2012-2014 -- WPI Suite
 *
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *******************************************************************************/
package edu.wpi.cs.wpisuitetng.modules.PlanningPoker.models;

import javax.swing.table.AbstractTableModel;

/**
 * A Table Model that holds data and actions for the Team Members' Estimations table
 * @author hlong290494
 */
public class TeamEstimateTableModel extends AbstractTableModel{
	/**
	 * Table column 
	 */
	String[] columnNames = {"Username", "Estimation"};
	
	/**
	 * Table data - contains team estimation
	 */
	Object[][] data = {
			{"Long", "1"},
			{"Chris", "2"},
			{"Ryan", "4"}
	};

    public int getColumnCount() {
        return columnNames.length;
    }

    public int getRowCount() {
        return data.length;
    }

    public String getColumnName(int col) {
        return columnNames[col];
    }

    public Object getValueAt(int row, int col) {
        return data[row][col];
    }

    public Class getColumnClass(int c) {
        return getValueAt(0, c).getClass();
    }

    public boolean isCellEditable(int row, int col) {
    	return false;
    }

    public void setValueAt(Object value, int row, int col) {
        data[row][col] = value;
        fireTableCellUpdated(row, col);
    }
}