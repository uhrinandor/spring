package com.spring.app.skeleton.models.vehicle;

import com.spring.app.skeleton.models.field.IField;
import com.spring.app.skeleton.utils.IEntity;

public interface IDriver extends IEntity {
    public IField nextMove();
    public void setNext(IField f);
    public void setCurrent(IField f);
    public IField getCurrent();
    public IField getNext();
}
