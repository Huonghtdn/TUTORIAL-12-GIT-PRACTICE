package demo;

import utils.AttrRef;
import utils.DOpt;
import utils.DomainConstraint;
import utils.OptType;

/**
 * @Overview Person is a human that is characterised by attributes such as id, name, phone.
 * 
 * @attributes
 * 	id			Integer			int
 * 	name		String			String
 * 	phone		MobilePhone		MobilePhone
 * 
 * @object
 * 	A typical Person is<i, n, p> where id[i], name[n], phone[p]
 * 
 * @abstract_properties
 * 	mutable[id] = false/\ optional[id] = false/\ min[id] = 1/\
 * 	mutable[name] = true/\optional[name] = false/\length[name] = 30/\
 * 	mutable[phone] = true/\optional[phone] = true
 * @author thuhuong
 *
 */
public class Person {
	//constant
	private static final int MIN_ID = 1;
	private static final int LENGTH_NAME = 30;
	
	//representation
	@DomainConstraint(type = "Integer", mutable = false, optional = false, min=MIN_ID)
	@AttrRef("id")
	private int id;
	@DomainConstraint(type="String", mutable = true, optional = false, length=LENGTH_NAME)
	@AttrRef("name")
	private String name;
	@DomainConstraint(type="MobilePhone", mutable = true, optional = true)
	@AttrRef("phone")
	private MobilePhone phone;
	
	//constructor
	//Constructor for the case where no information
	public Person() {
		
	}
	/*	Constructor for the case where filled information
	 * 	@effect<pre>
	 * 	if any attribute is valid
	 * 		initialise this as<id, name, phone>
	 * 	else
	 * 		initialise this as<> and print error
	 * </pre>
	 */
	
	public Person(int id, String name, MobilePhone phone) {
		if(!validateId(id)) {
			System.err.println("Invalid id");
		return;
		}
		else if(!validateName(name)) {
			System.err.println("Invalid name");
			return;
		}
		else {
			this.id = id;
			this.name = name;
			this.phone = phone;
		}
	}
	//getters
	/**
	 * @effects<tt>
	 * return this.id
	 * </tt>
	 */
	public int getId() {
		return id;
	}
	public boolean validateId(int id) {
		if(id >=MIN_ID) {
			return true;
		}else
			return false;
	}
	
	/**
	 * @effects<tt>
	 * return this.name
	 * </tt>
	 */
	@DOpt(type = OptType.Observer)
	@AttrRef("name")
	public String getName() {
		return name;
	}
	/**
	 * @effects<pre>
	 * 	if name is valid
	 * 		set this.name = name
	 * 		return true
	 * 	else
	 * 		return false
	 * </pre>
	 */
	@DOpt(type = OptType.Mutator)
	@AttrRef("name")
	public void setName(String name) {
		if(validateName(name)) {
			this.name = name;
		}	
	}
	//name must consist of at least two words that are separated by a white space
	public boolean validateFormatName(String name) {
		if(name.contains(" ")) {
			return true;
		}else {
			System.out.println("Name must consist of at least two words!");
			return false;
		}
	}
	public boolean validateName(String name) {
		return(name!=null && name.length() >0 && name.length() <=LENGTH_NAME);
	}
	public boolean validatePhone(MobilePhone phone) {
		if (!phone.repOk()) {
			return false;
		}return true;
	}
	public void greet() {
		
	}
	public boolean equals(Object o) {
		if(!(o instanceof Person)) {
			return false;
		}
		if(!(this.repOk() ||( (Person) o).repOk())) {
			return false;
		}
		if(id != ((Person) o).id) {
			return false;
		}if(name.length() != ((Person) o).name.length()) {
			return false;
		}else {
			for(int i=0; i<name.length(); i++) {
				if(name.charAt(i)!=((Person) o).name.charAt(i)) {
					return false;
				}
			}
		}
		return true;
	}
	/**
	 * @effects<pre>
	 * 	if this satisfies abstract properties
	 * 		return true
	 * 	else
	 * 		return false
	 * @return
	 */
	public boolean repOk() {
		return (validateId(id) && validateName(name) &&validateFormatName(name));
	}
	public String toString() {
		return "Peerson: "+id+name+phone;
	}
	
	
	
}
