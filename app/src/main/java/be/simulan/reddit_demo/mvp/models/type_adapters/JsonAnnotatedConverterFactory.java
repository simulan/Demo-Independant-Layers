package be.simulan.reddit_demo.mvp.models.type_adapters;

import android.support.annotation.Nullable;

import java.lang.annotation.Annotation;
import java.lang.reflect.Type;

import be.simulan.reddit_demo.da.apis.BoxedArray;
import okhttp3.ResponseBody;
import retrofit2.Converter;
import retrofit2.Retrofit;

public class JsonAnnotatedConverterFactory extends Converter.Factory {
    private BoxedCommentsConverter boxedCommentsConverter = new BoxedCommentsConverter();
    String specialAnnotation = "BoxedArray";


    @Nullable @Override
    public Converter<ResponseBody, ?> responseBodyConverter (Type type, Annotation[] annotations, Retrofit retrofit) {
        if(hasSpecialAnnotation(annotations)) {
            return boxedCommentsConverter;
        }else{
            return super.responseBodyConverter(type, annotations, retrofit);
        }
    }
    private boolean hasSpecialAnnotation(Annotation[] annotations) {
        for(Annotation annot : annotations) {
            if(BoxedArray.class==annot.annotationType()) {
                return true;
            }
        }
        return false;
    }
}
