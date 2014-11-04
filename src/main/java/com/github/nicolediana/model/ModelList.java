package com.github.nicolediana.model;

import java.util.List;

/**
 * Created by pcnicole on 30/10/2014.
 */

public class ModelList<Child extends Model> extends JsonArray<Child> {

  public ModelList(List<Child> list) {
    super(list);
  }

}
