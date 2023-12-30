package me.fernndinho.shop.files.payload;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@AllArgsConstructor @Getter @Setter
public class MultipleFilesResponse {
    private List<FileResponse> images;
}
