package cn.sjlm.sjlmdao;

import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

public interface HelloDao {

    List<Map<String,Object>> select();
}
