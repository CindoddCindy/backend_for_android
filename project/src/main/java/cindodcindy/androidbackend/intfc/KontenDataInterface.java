package cindodcindy.androidbackend.intfc;

import cindodcindy.androidbackend.model.KontenData;

public interface KontenDataIntreface{
    List<KontenData> findAll(int page, int limit);
    Long save(@NotNull KontenData kontenData);
    Long size();
    KontenData findById(@NotNull Long id);
    boolean update (@NotNull Long id, String keterangan, String gambar);
    boolean destroy (@NotNull Long id);



}