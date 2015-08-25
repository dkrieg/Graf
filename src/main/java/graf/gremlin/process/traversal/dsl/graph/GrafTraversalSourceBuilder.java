package graf.gremlin.process.traversal.dsl.graph;

import org.apache.tinkerpop.gremlin.process.traversal.TraversalEngine;
import org.apache.tinkerpop.gremlin.process.traversal.TraversalSource.Builder;
import org.apache.tinkerpop.gremlin.process.traversal.TraversalStrategy;
import org.apache.tinkerpop.gremlin.process.traversal.dsl.graph.GraphTraversalSource;
import org.apache.tinkerpop.gremlin.structure.Graph;

public class GrafTraversalSourceBuilder implements Builder<GrafGraphTraversalSource> {
    @Override
    public Builder engine(TraversalEngine.Builder engine) {
        builder.engine(engine);
        return this;
    }

    @Override
    public Builder with(TraversalStrategy strategy) {
        builder.with(strategy);
        return this;
    }

    @Override
    public Builder without(Class<? extends TraversalStrategy> strategyClass) {
        builder.without(strategyClass);
        return this;
    }

    @Override
    public GrafGraphTraversalSource create(Graph graph) {
        return new GrafGraphTraversalSource(builder.create(graph), this);
    }

    private final GraphTraversalSource.Builder builder = GraphTraversalSource.build();
}