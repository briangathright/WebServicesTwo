package webservicetwo.webservicetwo;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "customer")
public class Customer implements Serializable {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "customer_id")
	private long customer_id;
	
	@Column(name = "customer_name")
	private String name;
	
	public Customer()
	{
	}
	
	public Customer(String name)
	{
		this.name=name;
	};
	
	public long getID()
	{
		return customer_id;
	}
	
	public void setID(long id)
	{
		this.customer_id=id;
	}
	
	public String getName()
	{
		return name;
	}
	
	public void setName(String name)
	{
		this.name=name;
	}
	
	@Override
	public String toString() {
		return "Customer " + customer_id + ":" +
				"\nName: " + name;
	}
}