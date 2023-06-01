package com.harshit.blog.api.payloads;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class CategoryDto {
    private String id;
    @NotEmpty(message = "Category title should not be empty")
    @Size(min = 2, max = 20)
    private String title;
    @NotEmpty(message = "Category title should not be empty")
    private String description;
}
