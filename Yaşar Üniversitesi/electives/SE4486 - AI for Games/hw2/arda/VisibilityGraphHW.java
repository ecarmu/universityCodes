package chapter3.pathfinding.alg.astar.hw2.arda;

import chapter3.pathfinding.Connection;
import chapter3.pathfinding.visibility.GeomUtils;
import chapter3.pathfinding.visibility.Graph2D;
import math.geom2d.Point2D;
import math.geom2d.polygon.Polygon2D;
import org.newdawn.slick.geom.GeomUtil;

import java.util.*;
import java.util.function.DoubleToIntFunction;

public class VisibilityGraphHW implements Graph2D {

    List<Point2D> points;
    List<Polygon2D> polygons;
    // TODO: You can define necessary additional members here


    public VisibilityGraphHW(List<Point2D> points, List<Polygon2D> polygons) {
        this.points = points;
        this.polygons = polygons;
        buildGraph();
    }

    /**
     * TODO: you can do whatever you want to do initially here
     */
    private void buildGraph() {

    }

    /**
     * TODO: Implement properly
     *
     * @return
     */
    @Override
    public List<Point2D> getNodes() {
        return points;
    }

    /**
     * TODO:Implement properly
     *
     * @return
     */
    @Override
    public List<Polygon2D> getPolygons() {
        return polygons;
    }

    /**
     * TODO:Implement properly
     *
     * @param from
     * @return
     */
    @Override
    public List<Connection<Point2D>> connectionsOf(Point2D from) {
        List<Connection<Point2D>> connectionList = new ArrayList<>();

        for (Point2D point : points) {
            if (GeomUtils.isVisible(from, point, polygons))
                connectionList.add(new Connection<Point2D>() {
                    @Override
                    public Point2D from() {
                        return from;
                    }

                    @Override
                    public Point2D to() {
                        return point;
                    }

                    @Override
                    public double cost() {
                        return Point2D.distance(from, point);
                    }
                });
        }

        for (Polygon2D polygon : polygons) {
            for (Point2D vertex : polygon.vertices()) { // Assuming getVertices() returns the vertices of the polygon
                if (GeomUtils.isVisible(from, vertex, polygons)) {
                    connectionList.add(new Connection<Point2D>() {
                        @Override
                        public Point2D from() {
                            return from;
                        }

                        @Override
                        public Point2D to() {
                            return vertex;
                        }

                        @Override
                        public double cost() {
                            return Point2D.distance(from, vertex);
                        }
                    });
                }
            }
        }

        return connectionList;
    }

    @Override
    public List<? extends Connection<Point2D>> getConnections() {
        List<Connection<Point2D>> connectionList = new ArrayList<>();

        for (Point2D pointFrom : points) {
            for (Point2D pointTo : points) {
                if (GeomUtils.isVisible(pointFrom, pointTo, polygons))
                    connectionList.add(new Connection<Point2D>() {
                        @Override
                        public Point2D from() {
                            return pointFrom;
                        }

                        @Override
                        public Point2D to() {
                            return pointTo;
                        }

                        @Override
                        public double cost() {
                            return Point2D.distance(pointFrom, pointTo);
                        }
                    });
            }
        }

        for (Point2D pointFrom : points) {
            for (Point2D pointTo : points) {
                if (GeomUtils.isVisible(pointFrom, pointTo, polygons)) {
                    connectionList.add(new Connection<Point2D>() {
                        @Override
                        public Point2D from() {
                            return pointFrom;
                        }

                        @Override
                        public Point2D to() {
                            return pointTo;
                        }

                        @Override
                        public double cost() {
                            return Point2D.distance(pointFrom, pointTo);
                        }
                    });
                }
            }
        }

        for (Polygon2D polygon : polygons) {
            for (Point2D vertex : polygon.vertices()) {
                for (Point2D point : points) {
                    if (GeomUtils.isVisible(point, vertex, polygons)) {
                        connectionList.add(new Connection<Point2D>() {
                            @Override
                            public Point2D from() {
                                return point;
                            }

                            @Override
                            public Point2D to() {
                                return vertex;
                            }

                            @Override
                            public double cost() {
                                return Point2D.distance(point, vertex);
                            }
                        });
                    }
                }

                for (Polygon2D otherPolygon : polygons) {
                    for (Point2D otherVertex : otherPolygon.vertices()) {
                        if (GeomUtils.isVisible(vertex, otherVertex, polygons)) {
                            connectionList.add(new Connection<Point2D>() {
                                @Override
                                public Point2D from() {
                                    return vertex;
                                }

                                @Override
                                public Point2D to() {
                                    return otherVertex;
                                }

                                @Override
                                public double cost() {
                                    return Point2D.distance(vertex, otherVertex);
                                }
                            });
                        }
                    }
                }
            }
        }

        return connectionList;
    }
}
