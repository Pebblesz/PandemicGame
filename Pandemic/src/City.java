public class City {
    private String name;
    private boolean hasResearchStation;
    private int numDiseaseCubes;
    
    public City(String name, boolean hasResearchStation, int numDiseaseCubes) {
        this.name = name;
        this.hasResearchStation = hasResearchStation;
        this.numDiseaseCubes = numDiseaseCubes;
    }
    
    public String getName() {
        return name;
    }
    
    public boolean hasResearchStation() {
        return hasResearchStation;
    }
    
    public int getNumDiseaseCubes() {
        return numDiseaseCubes;
    }

	public void addDiseaseCube() {
		this.numDiseaseCubes++;
	}
	
	public void treatDiseaseCube() {
		this.numDiseaseCubes--;
	}
	public void buildReseachStation() {
		this.hasResearchStation = true;
	}
}