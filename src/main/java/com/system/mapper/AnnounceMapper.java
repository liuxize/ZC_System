package com.system.mapper;


import java.util.List;

public interface AnnounceMapper {
}

package com.system.mapper;
        import com.system.po.Birthday;
        import com.system.po.PagingVO;

        import java.util.List;

public interface AnnounceMapper {
    int insert(Birthday birthday);

    int deleteByID(Integer id);

    List<Birthday> selectAll (PagingVO pagingVO);

    int countBirth();
}
