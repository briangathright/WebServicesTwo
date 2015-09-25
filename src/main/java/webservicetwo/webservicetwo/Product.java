package webservicetwo.webservicetwo;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.FetchType;

@Entity
@Table(name = "product")
public class Product implements Serializable {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "product_id")
	private long product_id;
	
	@Column(name = "detail")
	private String detail;
		
	public Product()
	{
		
	};
	
	public Product(String detail)
	{
		this.detail=detail;
	}
	
	public long getID()
	{
		return product_id;
	}
	
	public void setID(long id)
	{
		this.product_id=id;
	}
	
	public String getDetail()
	{
		return detail;
	}
	
	public void setDetail(String detail)
	{
		this.detail=detail;
	}
}