package demo;

import utils.DomainConstraint;
/**
 * @overview MobilePhone is a object that is characteristised by attribute such as
 * 				 manName, model, color year, guaranteed.
 * @attributes
 * 	manName		String		String
 * 	model		String		String
 * 	color		Character	char
 * 	year		Integer		int
 * 	guaranteed	Boolean		boolean
 * 
 * @object
 * 	A typical MobilePhone is <n, m, c, y, g> where manName[n], model[m],
 * 							 color[c], year[y], guaranteed[g].
 * 
 * @abstract_properties
 * 	mutable[manName]=true/\optional[manName]=false/\length[manName]=30/\
 * 	mutable[model]=true/\optional[model]=false/\length[model]=20/\
 * 	mutable[color]=true/\optional[color]=false/\
 * 	mutable[year]=true/\optional[color]=false/\max[year]=2020/\
 * 	mutable[guaranteed]=true/\optional[guaranteed]=false.
 *  	
 * @author thuhuong
 *
 */
public class MobilePhone {
	//constant
	private static final int LENGTH_MANNAME = 30;
	private static final int LENGTH_MODEL = 20;
	private static final int MAX_YEAR = 2020;
	
	//representation
	@DomainConstraint(type = "String", mutable=true, optional=false, length=LENGTH_MANNAME)
	private String manName;
	@DomainConstraint(type = "String", mutable=true, optional=false, length=LENGTH_MODEL)
	private String model;
	@DomainConstraint(type = "enum", mutable=true, optional=false)
	private Color color;
	@DomainConstraint(type = "Integer", mutable=true, optional=false, max=MAX_YEAR)
	private int year;
	@DomainConstraint(type = "Boolean", mutable=true, optional=false)
	private boolean guaranteed;
	
	//constructor
	public MobilePhone(String manName, String model, Color color, int year, boolean guaranteed) {
		if(validateModel(model)) {
			this.manName = manName;
			this.model = model;
			this.color = color;
			this.year = year;
			this.guaranteed = guaranteed;
		}else {
			System.out.println("Format of model is not correct!");
		}
		
	}
	
	
	public String getManName() {
		return manName;
	}


	public void setManName(String manName) {
		this.manName = manName;
	}


	public String getModel() {
		return model;
	}


	public void setModel(String model) {
		this.model = model;
	}


	public int getYear() {
		return year;
	}


	public void setYear(int year) {
		this.year = year;
	}


	public boolean isGuaranteed() {
		return guaranteed;
	}


	public void setGuaranteed(boolean guaranteed) {
		this.guaranteed = guaranteed;
	}


	public Color getColor() {
		return color;
	}

		// attribute model must be of the form M-ABC-MNP, 
		//where ABC is a 3-letter word and MNP is a 3-digit word. 
		//For example, M-SAM-123 is a valid phone model, but M-S0M-123 is not
	public boolean validateModel(String model) {
//		String s1 = model.substring(0,  1);
//		String s2 = model.substring(2, 4);
//		char [] 
//		String s3 = model.substring(5);
//		String s4 = model.substring(6, 8);
		char [] modelToChar = model.toCharArray();
		if(modelToChar[0] == 'M' && modelToChar[1] == '-' && modelToChar[5] =='-') {
			for(int i = 2; i<4; i++) {
				if(modelToChar[i] >= 'A' && modelToChar[i] >='Z') {
					continue;
				}
			}for(int i=6; i<8;i++) {
				if(modelToChar[i] >= '0' && modelToChar[i] >='9') {
					continue;
				}
			}return true;
		}else {
			return false;
		}
	}
	public boolean repOk() {
		return false;
	}
}
