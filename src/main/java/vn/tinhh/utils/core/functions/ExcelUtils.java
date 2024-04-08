package vn.tinhh.utils.core.functions;

import org.apache.logging.log4j.util.Strings;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.IOException;
import java.io.InputStream;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;

public class ExcelUtils {
    public static final int COLUMN_INDEX_ID = 0;
    public static final int COLUMN_INDEX_NAME = 1;
    public static final int COLUMN_INDEX_ADDRESS = 2;
    public static final int COLUMN_INDEX_GROUP = 3;
    public static final int COLUMN_INDEX_AREA = 4;
    public static final int COLUMN_INDEX_TAX_CODE = 5;
    public static final int COLUMN_INDEX_REPRESENTATIVE = 6;
    public static final int COLUMN_INDEX_PHONE = 7;
    public static final int COLUMN_INDEX_EMAIL = 8;
    public static final int COLUMN_INDEX_SALE = 9;

    public static boolean isRowEmpty(Row row, int length) {
        for (int c = row.getFirstCellNum(); c < length; c++) {
            Cell cell = row.getCell(c);
            if (cell != null && cell.getCellType() != CellType.BLANK && cell.getCellType() != CellType._NONE)
                return false;
        }
        return true;
    }

    public static String getValue(XSSFCell inputCell) {
        String input = inputCell == null || inputCell.getCellType() == CellType.BLANK ? null : getValueCell(inputCell);
        return input == null ? Strings.EMPTY : input.trim();
    }

    public static String getValueCell(XSSFCell inputCell) {
        if (inputCell.getCellType() != CellType.STRING
                && inputCell.getCellType() != CellType.FORMULA
                && DateUtil.isCellDateFormatted(inputCell)) {
            SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
            return sdf.format(inputCell.getDateCellValue());
        } else if (inputCell.getCellType() == CellType.NUMERIC) {
            DecimalFormat df = new DecimalFormat();
            df.setGroupingUsed(false);
            return df.format(inputCell.getNumericCellValue());

        }
        return inputCell.getStringCellValue();
    }


    private static String formatToString(Object cellValue) {
        if (cellValue instanceof Double) {
            DecimalFormat df = new DecimalFormat();
            df.setGroupingUsed(false);
            String valueStr = df.format(cellValue);
            if (!valueStr.contains(".")) {
                return valueStr;
            } else {
                return Double.toString((Double) cellValue);
            }
        } else {
            return (String) cellValue;
        }
    }

    private static Workbook getWorkbook(InputStream inputStream, String excelFilePath) throws IOException {
        Workbook workbook = null;
        if (excelFilePath.endsWith("xlsx")) {
            workbook = new XSSFWorkbook(inputStream);
        } else if (excelFilePath.endsWith("xls")) {
            workbook = new HSSFWorkbook(inputStream);
        } else {
            throw new IllegalArgumentException("Định dạng file excel không hợp lệ");
        }

        return workbook;
    }

    private static XSSFWorkbook getXSSFWorkbook(InputStream inputStream, String excelFilePath) throws IOException {
        XSSFWorkbook workbook = null;
        if (excelFilePath.endsWith("xlsx")) {
            workbook = new XSSFWorkbook(inputStream);
        } else {
            throw new IllegalArgumentException("Định dạng file excel không hợp lệ");
        }

        return workbook;
    }

    private static Object getCellValue(Cell cell) {
        CellType cellType = cell.getCellType();
        Object cellValue = null;
        switch (cellType) {
            case BOOLEAN:
                cellValue = cell.getBooleanCellValue();
                break;
            case FORMULA:
                Workbook workbook = cell.getSheet().getWorkbook();
                FormulaEvaluator evaluator = workbook.getCreationHelper().createFormulaEvaluator();
                CellValue cellValue1 = evaluator.evaluate(cell);
                switch (cellValue1.getCellType()) {
                    case STRING:
                        cellValue = cellValue1.getStringValue();
                        break;
                    case NUMERIC:
                        cellValue = cellValue1.getNumberValue();
                        break;
                    default:
                        break;
                }
                break;
            case NUMERIC:
                cellValue = cell.getNumericCellValue();
                break;
            case STRING:
                cellValue = cell.getStringCellValue();
                break;
            case _NONE:
            case BLANK:
            case ERROR:
                break;
            default:
                break;
        }

        return cellValue;
    }
}
