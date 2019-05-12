package com.test.calculator.core.common.mapper;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.test.calculator.core.common.CommonConstants;
import com.test.calculator.core.common.exception.CalculatorException;
import com.test.calculator.core.common.message.Messages;
import com.test.calculator.core.common.util.SearchCriteria;
import lombok.Getter;
import lombok.Setter;
import org.apache.logging.log4j.util.Strings;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;

import javax.servlet.http.HttpServletRequest;
import javax.xml.bind.annotation.XmlRootElement;
import java.io.IOException;
import java.io.Serializable;
import java.util.*;
import java.util.stream.Collectors;

@Setter
@XmlRootElement(name = "data")
public class CustomParamPageFilter implements Serializable {

    @Getter
    private List<SearchCriteria> filters;

    private InternalSerializePage pageable;

    @Getter
    private String search;

    private Messages messages;

    private Integer pageSize;

    public CustomParamPageFilter() {
        this.search = Strings.EMPTY;
        this.pageSize = 200;
    }

    public Pageable getPageable() {
        return PageRequest.of(Objects.nonNull(pageable) ? pageable.getPage() : 0,
                Objects.nonNull(pageable) ? pageable.size : pageSize,
                (Objects.nonNull(pageable) && Objects.nonNull(pageable.orders) && pageable.orders.size() > 0) ?
                        Sort.by(pageable.orders.stream()
                            .map(order -> new Sort.Order(order.direction, order.property))
                            .collect(Collectors.toList())): Sort.by(Sort.Direction.DESC, "id"));
    }

    public static CustomParamPageFilter mapRequestData(HttpServletRequest request)
            throws CalculatorException {
        CustomParamPageFilter data = new CustomParamPageFilter();
        if (Objects.nonNull(
                request.getParameter(CommonConstants.CUSTOM_PARAM_PAGEABLE_DATA))) {
            ObjectMapper mapper = new ObjectMapper();
            try {
                data = mapper.readValue(
                        request.getParameter(CommonConstants.CUSTOM_PARAM_PAGEABLE_DATA),
                        CustomParamPageFilter.class);
            }
            catch (IOException e) {
                throw new CalculatorException(HttpStatus.BAD_REQUEST,
                        e.getLocalizedMessage());
            }
        }

        if (Objects.isNull(request.getParameter(CommonConstants.CUSTOM_PARAM_PAGEABLE_DATA)) ||
                Objects.isNull(data.getFilters())) {
                    data.setFilters(new ArrayList<SearchCriteria>());
        }

        return data;
    }

    @Setter
    static class InternalSerializePage {

        private int page;

        private int size;

        private List<CustomOrder> orders;

        public InternalSerializePage() {
            super();
        }

        int getPage() {
            return page != 0 ? page - 1 : 0;
        }

    }

    @Setter
    static class CustomOrder {

        private Sort.Direction direction;

        private String property;

        public CustomOrder() {
            super();
        }

    }

}
