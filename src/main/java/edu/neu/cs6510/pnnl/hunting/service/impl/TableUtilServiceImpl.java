package edu.neu.cs6510.pnnl.hunting.service.impl;

import edu.neu.cs6510.pnnl.hunting.mapper.TableUtilMapper;
import edu.neu.cs6510.pnnl.hunting.service.TableUtilService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class TableUtilServiceImpl implements TableUtilService {

    private static final String VAV_REGEX = "vav\\d{3}[a-z]?";

    @Resource
    TableUtilMapper mapper;

    @Override
    public List<String> getAllTable() {
        return mapper.getAllTable();
    }

    @Override
    public List<String> getAllVavTable() {
        List<String> allTables = mapper.getAllTable();
        return allTables.stream()
                .filter(s->s.matches(VAV_REGEX))
                .collect(Collectors.toList());
    }
}
