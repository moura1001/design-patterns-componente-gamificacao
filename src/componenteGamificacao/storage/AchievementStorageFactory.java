package componenteGamificacao.storage;

public abstract class AchievementStorageFactory {
	
	private static AchievementStorage storage;
	
	public static void setAchievementStorage(AchievementStorage a) {
		storage = a;
	}
	
	public static AchievementStorage getAchievementStorage() {
		if (storage == null)
			storage = new MemoryAchievementStorage();
		
		return storage;
	}

}
