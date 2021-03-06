import React, { Component } from 'react';
import PropTypes from 'prop-types'
import { connect } from 'react-redux'
import { createProject} from '../../actions/ProjectAction'
import classnames from 'classnames'

class AddProject extends Component {
    constructor() {
        super()

        this.state={
            projectName: '',
            identifier: '',
            description:'',
            startDate:'',
            endDate: '',
            errors:{}
        };
        this.onChange = this.onChange.bind(this);
        this.onSubmit = this.onSubmit.bind(this);
    }
    componentWillReceiveProps(nextProps) {
        if(nextProps.errors){
            this.setState({errors:nextProps.errors})
        }
    }
    onChange (e) {
        this.setState({
            [e.target.name]:e.target.value
        })
    }

    onSubmit(e){
        e.preventDefault()
        const newProject = {
            projectName: this.state.projectName,
            identifier: this.state.identifier,
            description:this.state.description,
            startDate:this.state.startDate,
            endDate: this.state.endDate

        }

        this.props.createProject(newProject, this.props.history)

    }
    render() {
        const {errors} = this.state

        return (
            <div>
                
            <div className="project">
            <div className="container">
                <div className="row">
                    <div className="col-md-8 m-auto">
                        <h5 className="display-4 text-center">Create Project form</h5>
                        <hr />
                        <form onSubmit={this.onSubmit}>
                            <div className="form-group">
                                <input type="text" className={classnames("form-control form-control-lg " , {'is-invalid':errors.projectName})} placeholder="Project Name" name='projectName' value={this.state.projectName} onChange={this.onChange}/>
                                {errors.projectName && (
                                    <div className='invalid-feedback'>{errors.projectName}</div>
                                )}
                            </div>
                            <div class="form-group">
                                <input type="text" className={classnames("form-control form-control-lg " , {'is-invalid':errors.identifier})} placeholder="Unique Project ID" name='identifier' value={this.state.identifier} onChange={this.onChange}
                                     />
                                     {errors.projectName && (
                                    <div className='invalid-feedback'>{errors.identifier}</div>
                                )}
                            </div>
                            <div className="form-group">
                                <textarea className={classnames("form-control form-control-lg " , {'is-invalid':errors.description})} placeholder="Project Description" name='description' value={this.state.description} onChange={this.onChange}></textarea>
                            {errors.projectName && (
                                    <div className='invalid-feedback'>{errors.description}</div>
                                )}
                            </div>
                            <h6>Start Date</h6>
                            <div className="form-group">
                                <input type="date" className="form-control form-control-lg" name="startDate" value={this.state.startDate} onChange={this.onChange} />
                            </div>
                            <h6>Estimated End Date</h6>
                            <div className="form-group">
                                <input type="date" className="form-control form-control-lg" name="endDate" value={this.state.endDate} onChange={this.onChange} />
                            </div>
    
                            <input type="submit" className="btn btn-primary btn-block mt-4" />
                        </form>
                    </div>
                </div>
            </div>
        </div>
        </div>
        );
    }
}

const mapStateToProps = state =>({
    errors: state.errors
})

AddProject.propTypes = {
    createProject : PropTypes.func.isRequired,
    errors: PropTypes.object.isRequired
}

export default connect (mapStateToProps, {createProject})(AddProject);