package model;
import java.io.File;
import java.time.LocalDate;

import javax.servlet.http.Part;

import util.StringUtils;


/**
 * Servlet implementation class EmployeeModel
 */



public class EmployeeModel {
    private String first_name;
    private String last_name;
    private LocalDate dob;
    private String gender;
    private LocalDate hire_date;
    private String phonenumber;
    private String email;
    private String password;
    private String imageUrlFromPart;

    // Constructors
    public EmployeeModel()     {
    	// Default constructor
    }
    

    public EmployeeModel ( String first_name, String last_name,
    		LocalDate dob, String gender, LocalDate hire_date, 
    		String phonenumber, String email, String password, Part profile_image) 
    {
        this.first_name = first_name;
        this.last_name = last_name;
        this.dob = dob;
        this.gender = gender;
        this.hire_date = hire_date;
        this.phonenumber = phonenumber;
        this.email = email;
        this.password = password;
        this.imageUrlFromPart = getImageUrl(profile_image);
        }

    
    
    // Getters and Setters
    
	public String getFirst_name() {
        return first_name;
    }

    public void setFirst_name(String first_name) {
        this.first_name = first_name;
    }

    public String getLast_name() {
        return last_name;
    }

    public void setLast_name(String last_name) {
        this.last_name = last_name;
    }

    public LocalDate getDob() {
        return dob;
    }

    public void setDob(LocalDate dob) {
        this.dob = dob;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public LocalDate getHire_date() {
        return hire_date;
    }

    public void setHire_date(LocalDate hire_date) {
        this.hire_date = hire_date;
    }

    public String getPhonenumber() {
        return phonenumber;
    }

    public void setPhonenumber(String phonenumber) {
        this.phonenumber= phonenumber;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
    
	public String getPassword() {
		return password;
	}
	
	public void setPassword(String password) {
		this.password = password;
	}

	public void setImageUrlFromPart(Part part) {
		this.imageUrlFromPart = getImageUrl(part);
	}
	
	public void setImageUrlFromDB(String imageUrl) {
		this.imageUrlFromPart = imageUrl;
	}
	
	public String getImageUrlFromPart() {
		return imageUrlFromPart;
    }
	
	
	/**
	 * This method extracts the image file name from the request part containing the uploaded image.
	 * 
	 * @param part The request part containing the uploaded image data.
	 * @return The extracted image file name, or "download.jpg" if no file name is found.
	 * @throws IOException If an error occurs while accessing the part data.
	 */
	private String getImageUrl(Part part) {
		 if (part == null) {
		        return "default.jpeg"; // Return a default image file name if the Part object is null
		    }
		// Define the directory path to store uploaded user images. This path should be configured elsewhere in the application.
	    String savePath = StringUtils.IMAGE_DIR_USER;
	    System.out.println("Writing file to: " + savePath);


	    // Create a File object representing the directory to store uploaded images.
	    File fileSaveDir = new File(savePath);

	    // Initialize the variable to store the extracted image file name.
	    String imageUrlFromPart = null;

	    // Check if the directory to store uploaded images already exists.
	    if (!fileSaveDir.exists()) {
	        // If the directory doesn't exist, create it.
	    	// user mkdirs() method to create multiple or nested folder
	        fileSaveDir.mkdir();
	    }

	    // Get the Content-Disposition header from the request part. This header contains information about the uploaded file, including its filename.
	    String contentDisp = part.getHeader("content-disposition");

	    // Split the Content-Disposition header into individual parts based on semicolons.
	    String[] items = contentDisp.split(";");

	    // Iterate through each part of the Content-Disposition header.
	    for (String s : items) {
	        // Check if the current part starts with "filename" (case-insensitive trim is used).
	        if (s.trim().startsWith("filename")) {
	            // Extract the filename from the current part.
	            // The filename is assumed to be enclosed in double quotes (").
	            // This part removes everything before the equal sign (=) and the double quotes surrounding the filename.
	            imageUrlFromPart = s.substring(s.indexOf("=") + 2, s.length() - 1);
	            break; // Exit the loop after finding the filename
	        }
	    }

	    // If no filename was extracted from the Content-Disposition header, set a default filename.
	    if (imageUrlFromPart == null || imageUrlFromPart.isEmpty()) {
	        imageUrlFromPart = "default.jpeg";
	    }

	    // Return the extracted or default image file name.
	    return imageUrlFromPart;
	}
	
	}
	

	
		    
		
			
		

		   
        
