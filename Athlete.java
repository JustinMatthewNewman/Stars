
/**
 * Player Object hold HTML input data of each distinct player.
 * 
 * @author justinnewman
 * @version 11/25/2020
 */
public class Athlete {
    private String name;
    private String jersyNum;
    private String curClass;
    private String height;
    private String weight;
    private String homeTown;
    private String genderId;
    private String pos;
    

    /**
     * 
     */
    public Athlete() {
        this.name = "";
        this.jersyNum = "";
        this.curClass = "";
        this.height = "";
        this.weight = "";
        this.homeTown = "";
        this.genderId = "";
        this.pos = "";
    }

    /**
     * 
     * @param name
     */
    public Athlete(String name, String jersyNum, String curClass, String height,
            String weight, String homeTown, String genderId, String pos) {
        if (name != null) {
            this.name = name;
        } else {
            this.name = "✞";
        }
        
        if (jersyNum != null) {
            this.jersyNum = jersyNum;
        } else {
            this.jersyNum = "✞";
        }
        
        if (curClass != null) {
            this.curClass = curClass;
        } else {
            this.curClass = "✞";
        }
      
        if (height != null) {
            this.height = height;
        } else {
            this.height = "✞";
        }
        
        if (weight != null) {
            this.weight = weight;
        } else {
            this.weight = "";
        }
        
        if (homeTown != null) {
            this.homeTown = homeTown;
        } else {
            this.homeTown = "✞";
        }
        if (genderId != null) {
            this.genderId = genderId;
        } else {
            this.genderId = "✞";
        }
        if (pos != null) {
            this.pos = pos;
            
        } else {
            this.pos = "✞";
        }
        
    }

    /**
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * @param name the name to set
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * @return the jersyNum
     */
    public String getJersyNum() {
        return jersyNum;
    }

    /**
     * @param jersyNum the jersyNum to set
     */
    public void setJersyNum(String jersyNum) {
        this.jersyNum = jersyNum;
    }

    /**
     * @return the curClass
     */
    public String getCurClass() {
        return curClass;
    }

    /**
     * @param curClass the curClass to set
     */
    public void setCurClass(String curClass) {
        this.curClass = curClass;
    }

    /**
     * @return the height
     */
    public String getHeight() {
        return height;
    }

    /**
     * @param height the height to set
     */
    public void setHeight(String height) {
        this.height = height;
    }

    /**
     * @return the weight
     */
    public String getWeight() {
        return weight;
    }

    /**
     * @param weight the weight to set
     */
    public void setWeight(String weight) {
        this.weight = weight;
    }

    /**
     * @return the homeTown
     */
    public String getHomeTown() {
        return homeTown;
    }

    /**
     * @param homeTown the homeTown to set
     */
    public void setHomeTown(String homeTown) {
        this.homeTown = homeTown;
    }

    /**
     * @return the genderId
     */
    public String getGenderId() {
        return genderId;
    }

    /**
     * @param genderId the genderId to set
     */
    public void setGenderId(String genderId) {
        this.genderId = genderId;
    }
}
