package view;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;
import java.util.function.Predicate;

public class ViewResolver {
    public static final String REDIRECT_SIGNATURE = "redirect: ";
    private static final String TEMPLATES_PREFIX = "/templates";
    private static final String TEMPLATES_SUFFIX = ".html";
    private static final String PATH_FORMAT = "%s%s%s";
    private static final Map<Predicate<String>, Function<String, View>> VIEWS = new HashMap<>();

    static {
        VIEWS.put(viewName -> viewName.startsWith(REDIRECT_SIGNATURE), RedirectView::new);
    }

    private ViewResolver() {
    }

    public static ViewResolver getInstance() {
        return ViewResolverLazyHolder.INSTANCE;
    }

    public View resolve(String viewName) {
        return VIEWS.entrySet().stream()
                .filter(entry -> entry.getKey().test(viewName))
                .map(entry -> entry.getValue().apply(viewName))
                .findAny()
                .orElse(new HandlebarsView(buildPath(viewName)));
    }

    private String buildPath(String viewName) {
        return String.format(PATH_FORMAT, TEMPLATES_PREFIX, viewName, TEMPLATES_SUFFIX);
    }

    private static class ViewResolverLazyHolder {
        private static final ViewResolver INSTANCE = new ViewResolver();
    }
}