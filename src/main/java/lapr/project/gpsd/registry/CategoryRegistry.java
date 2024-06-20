package lapr.project.gpsd.registry;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Formatter;
import java.util.List;
import java.util.Objects;
import lapr.project.gpsd.model.Category;
import lapr.project.gpsd.model.Client;

public class CategoryRegistry{
    private List<Category> categoryList;

    /**
     * Construtor
     * @param categoryList lista de categoriesList registadas
     */
    public CategoryRegistry(List<Category> categoryList) {
        this.categoryList = categoryList;
    }
    
    /**
     * Default constructor.
     */
    public CategoryRegistry(){
        this.categoryList = new ArrayList<>();
    }

    /**
     * Returns the list of Categories.
     * @return 
     */
    public List<Category> getCategoryList(){
        return this.categoryList;
    }
    
    /**
     * Sets the List to a given one.
     * @param categoryList 
     */
    public void setCategoryList(List<Category> categoryList){
        this.categoryList = categoryList;
    }
    
    /**
     * Returns the category with a input id.
     * @param catID
     * @return 
     */
    public Category getCategoryByID(String catID){
        for(Category cat: categoryList){
            if(cat.hasID(catID))
                return cat;
        }
        return null;
    }
    
    public Category getCategoryByDesignation(String desig){
        for(Category cat: categoryList){
            if(cat.hasDescription(desig))
                return cat;
        }
        return null;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;
        CategoryRegistry that = (CategoryRegistry) o;
        return categoryList.equals(that.categoryList);
    }

    /**
     * Instances a new category and returns it
     * @param catCode catgory code
     * @param desc category description
     * @return category
     */
    public Category newCategory(String catCode, String desc) {
        return new Category(catCode, desc);
    }

    /**
     * validates category
     * @param cat category to validate
     * @return true if its valid
     */
    public boolean validateCategory(Category cat) {
        //Validaes category
        return !categoryList.contains(cat);
    }

    /**
     * register a category to the registry list
     * @param cat category to register
     * @return true if its registered sucessfully 
     */
    public boolean registerCategory(Category cat) {
        return addCategory(cat);
    }

    /**
     * adds a category to the registry list
     * @param cat category to add
     * @return true if its added sucessfully
     */
    private boolean addCategory(Category cat) {
        return this.categoryList.add(cat);
    }
    
}