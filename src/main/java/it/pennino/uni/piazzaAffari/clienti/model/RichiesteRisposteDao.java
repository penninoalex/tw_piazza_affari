package it.pennino.uni.piazzaAffari.clienti.model;

public interface RichiesteRisposteDao {
	public void delete(RichiesteRisposte persistentInstance);
	public void save(RichiesteRisposte persistentInstance);
	public RichiesteRisposte findById(it.pennino.uni.piazzaAffari.clienti.model.RichiesteRisposteId id);

}
