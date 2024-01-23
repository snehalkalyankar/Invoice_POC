package firstproject;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;

import com.itextpdf.kernel.color.Color;
import com.itextpdf.kernel.geom.PageSize;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.kernel.pdf.colorspace.PdfDeviceCs.Gray;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.border.Border;
import com.itextpdf.layout.border.DashedBorder;
import com.itextpdf.layout.border.DottedBorder;
import com.itextpdf.layout.border.SolidBorder;
import com.itextpdf.layout.element.Cell;
import com.itextpdf.layout.element.Paragraph;
import com.itextpdf.layout.element.Table;
import com.itextpdf.layout.property.TextAlignment;


public class GeneratePdf {

	public static void main(String[] args) throws FileNotFoundException {
		// TODO Auto-generated method stub
		
		
	//=============================Step -1=======================================//	
		String path = "invoice.pdf";
		PdfWriter pdfWriter = new PdfWriter(path);
		PdfDocument pdfDocument = new PdfDocument(pdfWriter);
		pdfDocument.setDefaultPageSize(PageSize.A4);
		Document document = new Document(pdfDocument);
		
		//document.add(new Paragraph("hello coding error:"));
		
		//===========================Step-2============Create a two column and add that column in document=======================//
		
		float twocol = 285f;
		float twocol150 = twocol + 150;
		float twocolumnWidth [] = {twocol150,twocol};
		
		//===================Step -5 Create one Full with array which contain three column======================================//
		float threecolumn = 190f;
		//Below line can use to create three column of size 190 each============================================================
		float fullwidth[] = {threecolumn*3};
		
		//For product column
		float threecolumnwidth[] = {threecolumn,threecolumn,threecolumn};
		
		Paragraph paragraph = new Paragraph("\n");		
		Table table = new Table(twocolumnWidth);
		//==========================Step-3============If we want to break one cell into two cells then will divide that cell/2 two times====//
		table.addCell(new Cell().add("Invoice").setBorder(Border.NO_BORDER).setBold());
		Table nestedTable = new Table(new float[]{twocol/2,twocol/2});
		nestedTable.addCell(new Cell().add("Invoice No Is:").setBold().setBorder(Border.NO_BORDER));
		nestedTable.addCell(new Cell().add("2817383"));
		nestedTable.addCell(new Cell().add("Invoice date Is:").setBold().setBorder(Border.NO_BORDER));
		nestedTable.addCell(new Cell().add("22/1/2024"));
		
		//=======================This below line can  display two rows in single invoice column================//
		table.addCell(nestedTable.setBorder(Border.NO_BORDER));
	
		
		//=======================Step- 4=========ERRORRRRR==========================================================//
		@SuppressWarnings("deprecation")
		Border bgBorder = new DashedBorder(Color.BLACK,2f);
		
		//here we create the table with three column
		Table divider = new Table(fullwidth);
		divider.setBorder(bgBorder);
		
		
		//table.addCell(bg.setColor(Color.grey));
		
		
		table.addCell(new Cell().add("Invoice column--2").setBorder(Border.NO_BORDER));
		document.add(table);
		document.add(paragraph);
		document.add(divider);
		document.add(paragraph);
		
		
		//=================Step -6 =================================//
		
		Table twocolumnTable = new Table(twocolumnWidth);
		twocolumnTable.addCell(getBillingandShippingcell("Billing info"));
		twocolumnTable.addCell(getBillingandShippingcell("Shipping info"));
		document.add(twocolumnTable.setMarginBottom(12f));
		document.add(paragraph);
		
		
		//==========================step-7====This code is for product list part======================//
		
		Table tabledivide2 = new Table(fullwidth);
		Border bg = new DottedBorder(Color.BLACK,2f);
		document.add(tabledivide2.setBorder(bg).setBold());
		//Now will create paragraph with passing parameter as "Product"
		Paragraph para = new Paragraph("Product");
		document.add(para.setBold());
		
		Table threecolumnTable = new Table(threecolumnwidth);
		
		threecolumnTable.setBackgroundColor(Color.BLACK,12f);
		
		threecolumnTable.addCell(new Cell().add("Description").setBold().setFontColor(Color.WHITE));
		threecolumnTable.addCell(new Cell().add("Quantity").setBold().setFontColor(Color.WHITE).setTextAlignment(TextAlignment.CENTER));
		threecolumnTable.addCell(new Cell().add("Price").setBold().setFontColor(Color.WHITE).setTextAlignment(TextAlignment.RIGHT));
		document.add(threecolumnTable);
		
		
		//List of products
		List<Product> list = new ArrayList<>();
		list.add(new Product("apple", 2, 100));
		list.add(new Product("Strowberry", 2, 50));
		list.add(new Product("coconut", 2, 10));
		list.add(new Product("kiwe",2, 10));
		
		//We can interate the data of these three column
		Table threecolumntable2 = new Table(threecolumnwidth);
		
		float totalsum = 0.f;
		for(Product product : list) {
			
			//step 1= calculate the total price
			float total = product.getQuantity()*product.getPriceperpice();
			totalsum = total;
			threecolumntable2.addCell(new Cell().add(product.getPname()).setBorder(Border.NO_BORDER)).setMargin(10f);
			threecolumntable2.addCell(new Cell().add(String.valueOf(product.getQuantity())).setTextAlignment(TextAlignment.LEFT)).setBorder(Border.NO_BORDER);
			threecolumntable2.addCell(new Cell().add(String.valueOf(total)).setTextAlignment(TextAlignment.RIGHT).setBorder(Border.NO_BORDER));
			
			
			
		}
		document.add(threecolumntable2).setRightMargin(20f);
		
		document.close();
		
		System.out.println("pdf is generated.........");
		
	}

	private static Cell getBillingandShippingcell(String string) {
		// TODO Auto-generated method stub
		return new Cell().add(string).setFontSize(12f).setBold().setBorder(Border.NO_BORDER).setTextAlignment(TextAlignment.LEFT);
	}
	


}

class Product{
	private String pname;
	private int quantity;
	private float priceperpice;
	
	
	
	public String getPname() {
		return pname;
	}
	public void setPname(String pname) {
		this.pname = pname;
	}
	public int getQuantity() {
		return quantity;
	}
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	public float getPriceperpice() {
		return priceperpice;
	}
	public void setPriceperpice(float priceperpice) {
		this.priceperpice = priceperpice;
	}
	
	
	public Product(String pname, int quantity, float priceperpice) {
		super();
		this.pname = pname;
		this.quantity = quantity;
		this.priceperpice = priceperpice;
	}
	
	
	
	
	
	
}
